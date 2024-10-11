<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Success</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f9f9f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .thank-you-container {
            background-color: #fff;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
            width: 500px;
            padding: 40px;
            text-align: center;
        }

        .thank-you-container h1 {
            font-size: 28px;
            color: #4CAF50;
            margin-bottom: 10px;
        }

        .thank-you-container p {
            font-size: 18px;
            color: #333;
            margin-bottom: 20px;
        }

        .thank-you-container img {
            width: 100px;
            margin-bottom: 20px;
        }

        .back-button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        .back-button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>

    <div class="thank-you-container">
        <img src="https://img.icons8.com/ios-filled/100/4CAF50/checkmark.png" alt="Success Icon" />
        <h1>Cảm ơn bạn đã đặt hàng!</h1>
        <p>Chúng tôi đã nhận được đơn hàng của bạn và sẽ xử lý trong thời gian sớm nhất.</p>
        <a href="/" class="back-button">Back to Home</a>
    </div>

</body>
</html>
