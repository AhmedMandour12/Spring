<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item Management</title>

    <style>
        body {
            margin: 0;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        .container {
            text-align: center;
        }

        h1 {
            margin-bottom: 30px;
        }

        .buttons {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }

        .button {
            display: block;
            width: 200px;
            padding: 12px;
            text-decoration: none;
            color: white;
            background-color: #007bff;
            border-radius: 6px;
            font-size: 16px;
        }

        .button:hover {
            background-color: #0056b3;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>Item Management System</h1>

    <div class="buttons">
        <a href="/Item/add" class="button">Add Item</a>
        <a href="/Item/view" class="button">View Items</a>
    </div>

</div>

</body>
</html>