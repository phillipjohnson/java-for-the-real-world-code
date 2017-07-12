<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css" integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ" crossorigin="anonymous" />
  <title>New Order</title>
</head>
<body>
  <h2>New Order</h2>
  <div style="margin: 40px;">
    <form style="width: 500px;" method="POST">
      <div class="form-group">
        <label for="flavor">Flavor</label>
        <select class="form-control" id="flavor" name="flavor">
          <option value="" selected></option>
          <c:forEach items="${flavors}" var="flavor">
            <option value="${flavor}">${flavor.toString()}</option>
          </c:forEach>
        </select>
      </div>
      <div class="form-group">
        <label for="scoops">Scoops</label>
        <input class="form-control" type="text" id="scoops" name="scoops" />
      </div>
      <div class="form-group">
        <label for="toppingsContainer">Toppings</label>
        <div id="toppingsContainer">
          <c:forEach items="${toppings}" var="topping">
            <div class="checkbox">
              <label>
                <input type="checkbox" name="toppings" value="${topping}" />
                  &nbsp;${topping.toString()}
              </label>
            </div>
          </c:forEach>
        </div>
      </div>
      <button type="submit" class="btn btn-primary">Create Order</button>
    </form>
  </div>
</body>
</html>
