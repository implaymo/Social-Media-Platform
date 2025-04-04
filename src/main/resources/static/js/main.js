document.addEventListener("DOMContentLoaded", function () {
    // Define page elements
    const usernamePage = document.getElementById('username-page');
    const chatPage = document.getElementById('chat-page');
    const connectingElement = document.querySelector('.connecting');
    const messageInput = document.querySelector('#message');
    const messageForm = document.getElementById('messageForm');

    let stompClient = null;
    let username = "";

    // Debug: Log to confirm elements are available
    console.log("usernamePage:", usernamePage);
    console.log("chatPage:", chatPage);

    // This function handles the form submission for username
    function connect(event) {
        event.preventDefault();

        username = document.querySelector('#name').value.trim();
        console.log("USERNAME ENTER CHAT ", username)
        console.log("Username entered:", username);

        if (username) {
            // Try both class-based and direct style approaches
            usernamePage.classList.add('hidden');
            chatPage.classList.remove('hidden');

            // Force display styles directly as a fallback
            usernamePage.style.display = "none";
            chatPage.style.display = "flex";

            console.log("After hiding/showing, usernamePage visibility:", usernamePage.style.display);
            console.log("After hiding/showing, chatPage visibility:", chatPage.style.display);

            // Create WebSocket connection
            var socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);

            stompClient.connect({}, onConnected, onError);
        }
    }

    // This function runs once the WebSocket connection is established
    function onConnected() {

        // Subscribe to the public topic to receive messages
        stompClient.subscribe('/topic/public', onMessageReceived);
        const message = {
            sender: username,
            content: username + ' joined the chat!', // Add this line
            type: 'JOIN'
        };
        console.log("Sending message: ", JSON.stringify(message));
        stompClient.send("/app/addUser", {}, JSON.stringify(message));

        // Hide the connecting element
        connectingElement.classList.add('hidden');
    }

    // This function runs if there's an error connecting to the WebSocket server
    function onError(error) {
        connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
        connectingElement.style.color = 'red';
    }

    // This function handles incoming messages
    function onMessageReceived(payload) {
        console.log("Received message:", payload.body);
        var message = JSON.parse(payload.body);
        var messageElement = document.createElement('li');

        if (message.type === 'JOIN' || message.type === 'LEAVE') {
            messageElement.classList.add('event-message');
            messageElement.textContent = message.content || 
                (message.type === 'JOIN' ? `${message.sender} joined!` : `${message.sender} left!`);
        } else {
            messageElement.classList.add('chat-message');
            messageElement.innerHTML = `<strong>${message.sender}:</strong> ${message.content}`;
        }

        document.querySelector('#messageArea').appendChild(messageElement);
        // Auto scroll to the newest message
        messageElement.scrollIntoView({ behavior: "smooth" });
    }

    // Event listener for the username form submission
    document.getElementById('usernameForm').addEventListener('submit', connect);

    // Event listener for the message form submission
    messageForm.addEventListener('submit', sendMessage);

    // This function sends messages to the WebSocket server
    function sendMessage(event) {
        event.preventDefault();  // Prevent the default form submission

        var messageContent = messageInput.value.trim();
        if (messageContent && stompClient) {
            var chatMessage = {
                sender: username,
                content: messageInput.value,
                type: 'CHAT'
            };
            stompClient.send("/app/sendMessage", {}, JSON.stringify(chatMessage));
            messageInput.value = '';  // Clear the input field
        }
    }
});
