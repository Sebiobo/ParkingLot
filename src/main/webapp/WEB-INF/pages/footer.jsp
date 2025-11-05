<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<style>
    html, body {
        height: 100%;
        margin: 0;
    }
    body {
        display: flex;
        flex-direction: column;
    }
    main {
        flex: 1;
    }
    footer {
        background-color: #212529;
        color: white;
        text-align: center;
        padding: 1rem 0;
        width: 100%;
    }
</style>

<footer>
<div class="container">
    <span>&copy; <%= java.time.Year.now() %> Oboroceanu Sebastian</span>
</div>
</footer>