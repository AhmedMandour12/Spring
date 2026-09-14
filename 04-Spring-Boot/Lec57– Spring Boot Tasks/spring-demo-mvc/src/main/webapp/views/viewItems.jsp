<%@ page import="java.util.List" %>
<%@ page import="com.ahmed.springdemo.model.Item" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Items</title>

    <style>
        body {
            margin: 0;
            padding: 40px;
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
        }

        .container {
            width: 80%;
            margin: auto;
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
        }

        .search-box {
            display: flex;
            justify-content: center;
            margin-bottom: 25px;
        }

        .search-box input {
            width: 250px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px 0 0 5px;
            font-size: 14px;
        }

        .search-box button {
            padding: 10px 20px;
            border: none;
            background-color: #007bff;
            color: white;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
        }

        .search-box button:hover {
            background-color: #0056b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background-color: white;
        }

        th, td {
            padding: 12px;
            border: 1px solid #ccc;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: white;
        }

        .actions {
            display: flex;
            justify-content: center;
            gap: 10px;
        }

        .btn {
            padding: 8px 14px;
            border-radius: 5px;
            text-decoration: none;
            color: white;
            border: none;
            cursor: pointer;
            font-size: 14px;
        }

        .update {
            background-color: #28a745;
        }

        .delete {
            background-color: #dc3545;
        }

        .back {
            display: inline-block;
            margin-top: 20px;
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            text-decoration: none;
            border-radius: 5px;
        }
    </style>
</head>

<body>

<div class="container">

    <h1>All Items</h1>

    <!-- Search By ID -->
    <form action="/Item/search" method="get" class="search-box">

        <input type="number"
               name="id"
               placeholder="Enter Item ID"
               required>

        <button type="submit">
            Search
        </button>

    </form>


    <table>

        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Price</th>
            <th>Actions</th>
        </tr>

        <%
            List<Item> items = (List<Item>) request.getAttribute("items");

            if (items != null && !items.isEmpty()) {

                for (Item item : items) {
        %>

        <tr>

            <td><%= item.getId() %></td>

            <td><%= item.getName() %></td>

            <td><%= item.getPrice() %></td>

            <td>

                <div class="actions">

                    <a href="/Item/update/<%= item.getId() %>"
                       class="btn update">
                        Update
                    </a>

                    <form action="/Item/delete"
                          method="post"
                          style="display:inline;">

                        <input type="hidden"
                               name="id"
                               value="<%= item.getId() %>">

                        <button type="submit"
                                class="btn delete"
                                onclick="return confirm('Are you sure you want to delete this item?');">
                            Delete
                        </button>

                    </form>

                </div>

            </td>

        </tr>

        <%
            }

        } else {
        %>

        <tr>
            <td colspan="4">No items found</td>
        </tr>

        <%
            }
        %>

    </table>

    <a href="/Item/home" class="back">
        Back to Home
    </a>

</div>

</body>
</html>