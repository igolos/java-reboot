<html>
<head>
    <meta charset="UTF-8" />
    <style>
        body {
            font-family: Arial, sans-serif;
        }

        h3 {
            color: #007BFF;
        }

        form {
            width: 300px;
            margin: auto;
        }

        form div {
            margin-bottom: 10px;
        }

        form input {
            width: 100%;
            padding: 5px;
            box-sizing: border-box;
        }
    </style>
</head>
<body>
    <h3>Калькулятор доходности вклада</h3>
    <div>
        <form method="POST" action="finance">
            Сумма на момент открытия: <input name="sum">
            <br>
            Процентная ставка: <input name="percentage">
            <br>
            Количество лет: <input name="years">
            <br>
            <input type="submit" value="Посчитать">
        </form>
    </div>
</body>
</html>