<%-- 
    Document   : chatbox
    Created on : Nov 27, 2024, 1:52:57 PM
    Author     : Raymond
--%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <!-- Link to the external CSS file -->
    <style>
        #chatbox {
        position: fixed;
        bottom: 20px;
        right: 20px;
        width: 300px;
        max-height: 400px;
        border: 1px solid #ccc;
        border-radius: 10px;
        background: #fff;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);
        display: flex;
        flex-direction: column;
        font-family: Arial, sans-serif;
        }

    /* Chat Header */
        #chat-header {
            padding: 10px;
            background: #007bff;
            color: #fff;
            border-top-left-radius: 10px;
            border-top-right-radius: 10px;
            cursor: pointer;
            text-align: center;
        }

        /* Chat Content */
        #chat-content {
            flex: 1;
            padding: 10px;
            overflow-y: auto;
            display: none; /* Initially hidden */
        }

        /* Chat Footer */
        #chat-footer {
            display: flex;
            border-top: 1px solid #ccc;
            display: none; /* Initially hidden */
        }

        /* Text Input */
        #chat-input {
            flex: 1;
            padding: 5px;
            border: none;
            border-radius: 0;
            font-size: 14px;
        }

        /* Send Button */
        #send-btn {
            padding: 5px 10px;
            background: #007bff;
            color: #fff;
            border: none;
            cursor: pointer;
        }

        #send-btn:hover {
            background: #0056b3;
        }

        /* Your Messages */
        .message-sent {
            text-align: right;
            background-color: #e0f7fa;
            color: #00796b;
            margin: 5px 0;
            padding: 5px 10px;
            border-radius: 10px;
            display: inline-block;
            max-width: 80%;
        }

        /* Other Person's Messages */
        .message-received {
            text-align: left;
            background-color: #f1f8e9;
            color: #33691e;
            margin: 5px 0;
            padding: 5px 10px;
            border-radius: 10px;
            display: inline-block;
            max-width: 80%;
        }

    </style>

    <script>
        function toggleChat() {
            const chatContent = document.getElementById("chat-content");
            const chatFooter = document.getElementById("chat-footer");
            if (chatContent.style.display === "none") {
                chatContent.style.display = "block";
                chatFooter.style.display = "flex";
            } else {
                chatContent.style.display = "none";
                chatFooter.style.display = "none";
            }
        }

        function sendMessage() {
            const input = document.getElementById("chat-input");
            const message = input.value.trim();
            if (message) {
                const chatContent = document.getElementById("chat-content");

                // Add your message
                const yourMessage = document.createElement("div");
                yourMessage.className = "message-sent";
                yourMessage.textContent = message;
                chatContent.appendChild(yourMessage);

                // Scroll to the bottom
                chatContent.scrollTop = chatContent.scrollHeight;
                
                var xhr = new XMLHttpRequest();
                xhr.open("GET", "do.chat?message=" + encodeURIComponent(message), true);
                
                xhr.onreadystatechange = function() {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    var botResponse = xhr.responseText;
                    // Simulate a reply after a short delay
                    setTimeout(() => {
                    const replyMessage = document.createElement("div");
                    replyMessage.className = "message-received";
                    replyMessage.textContent = botResponse;
                    chatContent.appendChild(replyMessage);
                    chatContent.scrollTop = chatContent.scrollHeight;
                    }, 1000);
                }
                };

                xhr.send();

                

                // Clear the input
                input.value = "";
            }
        }
    </script>
</head>
<body>
    <!-- Chatbox -->
    <div id="chatbox">
        <!-- Chat Header -->
        <div id="chat-header" onclick="toggleChat()">Chat with us</div>
        <!-- Chat Content -->
        <div id="chat-content"></div>
        <!-- Chat Footer -->
        <div id="chat-footer">
            <input type="text" id="chat-input" placeholder="Type your message here...">
            <button id="send-btn" onclick="sendMessage()">Send</button>
        </div>
    </div>
</body>

