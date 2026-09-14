<%@ page import="com.ahmed.springdemo.model.Item" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Item</title>

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
            width: 350px;
            background: white;
            padding: 35px;
            border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0, 0, 0, 0.15);
        }

        h1 {
            text-align: center;
            margin-bottom: 25px;
        }

        label {
            display: block;
            margin-bottom: 6px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-bottom: 18px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            width: 100%;
            padding: 12px;
            border: none;
            border-radius: 5px;
            background-color: #28a745;
            color: white;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #218838;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 15px;
            text-decoration: none;
            color: #007bff;
        }
    </style>
</head>

<body>

<%
    Item item = (Item) request.getAttribute("item");
%>

<div class="container">

    <h1>Update Item</h1>

    <form action="/Item/update" method="post">

        <!-- ID is important for update -->
        <input type="hidden"
               name="id"
               value="<%= item.getId() %>">

        <label for="name">Item Name</label>

        <input type="text"
               id="name"
               name="name"
               value="<%= item.getName() %>"
               required>

        <label for="price">Price</label>

        <input type="number"
               id="price"
               name="price"
               value="<%= item.getPrice() %>"
               step="0.01"
               required>

        <button type="submit">
            Update Item
        </button>

    </form>

    <a href="/Item/view" class="back">
        Back to Items
    </a>

</div>

</body>
</html>