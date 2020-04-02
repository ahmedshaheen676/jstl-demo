
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    </head>
    <body>

        <div class="container">
            <h2>Search for employee</h2>

            <section name="seach by id">
                <div>
                    <form class="form" action="/JSTL/search" method="post">
                        <label for="name">enter employee name</label>
                        <input class="from-control" type="text" id="name" name="name" required>
                        <input type="submit" class="btn btn-primary" value="search for employee">
                    </form>
                </div>

                <c:if test="${!empty requestScope.employees}">

                    <table class="table table-striped table-bordered">
                        <thead class="thead-dark">


                        <td scope="col">first name</td>
                        <td scope="col">last name</td>
                        <td scope="col">email</td>
                        <td scope="col">phone</td>
                        </thead>
                        <tbody >
                            <c:forEach items="${requestScope.employees}" var="employee">
                                <tr scope="row">
                                    <td>${employee.getFristName()}
                                    </td>
                                    <td>${employee.getLastName()}
                                    </td>
                                    <td>${employee.getEmail()}
                                    </td>
                                    <td>${employee.getPhone()}
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </c:if>
                <c:if test="${!empty requestScope.errorMessage}">
                    <p>${requestScope.errorMessage}</p>
                </c:if>
            </section>
        </div>
        <script>
            var table = document.getElementById('table');

            for (var i = 1; i < table.rows.length; i++) {
                table.rows[i].onclick = function () {
                    //rIndex = this.rowIndex;
                    document.getElementById("firstName1").value = this.cells[0].innerHTML.trim();
                    document.getElementById("lastName1").value = this.cells[1].innerHTML.trim();
                    document.getElementById("email1").value = this.cells[2].innerHTML.trim();
                    document.getElementById("phone1").value = this.cells[3].innerHTML.trim();
                    document.getElementById("id").value = this.cells[4].innerHTML.trim();

                };
            }

        </script>
    </body>
</html>
