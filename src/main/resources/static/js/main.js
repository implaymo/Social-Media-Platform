document.addEventListener("DOMContentLoaded", function () {
    // Define page elements
    const usernamePage = document.getElementById('username-page');
    const chatPage = document.getElementById('chat-page');
    const connectingElement = document.querySelector('.connecting');
    const messageInput = document.querySelector('#message');
    const messageForm = document.getElementById('messageForm');

    let stompClient = null;
    let username = "";

    // This function handles the form submission for username
    function connect(event) {
        event.preventDefault();  // Prevent the form from reloading the page

        username = document.querySelector('#name').value.trim();  // Get the username

        if (username) {
            // Hide the username page and show the chat page
            usernamePage.classList.add('hidden');
            chatPage.classList.remove('hidden');

            // Create WebSocket connection
            var socket = new SockJS('/ws');
            stompClient = Stomp.over(socket);

            // Connect to the WebSocket server
            stompClient.connect({}, onConnected, onError);
        }
    }

    // This function runs once the WebSocket connection is established
    function onConnected() {
        // Subscribe to the public topic to receive messages
        stompClient.subscribe('/topic/public', onMessageReceived);

        // Tell the server the user has joined
        stompClient.send("/app/addUser", {}, JSON.stringify({ sender: username, type: 'JOIN' }));

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
        var message = JSON.parse(payload.body);

        // Handle the incoming message here (update UI, etc.)
        // For example, you could append the message to the message area:
        var messageElement = document.createElement('li');
        messageElement.classList.add('chat-message');
        messageElement.innerHTML = `<strong>${message.sender}:</strong> ${message.content}`;
        document.querySelector('#messageArea').appendChild(messageElement);
    }

    // Event listener for the form submission
    document.getElementById('usernameForm').addEventListener('submit', connect);

    // You may also want to handle message sending
    messageForm.addEventListener('submit', sendMessage);

    // This function sends messages to the WebSocket server
    function sendMessage(event) {
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
        event.preventDefault();  // Prevent the default form submission
    }
});
