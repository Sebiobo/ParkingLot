<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:pageTemplate pageTitle="Edit Car">

    <h1>Edit Car</h1>

    <a href="${pageContext.request.contextPath}/Cars"
       class="btn btn-secondary mb-3">Back</a>

    <form class="needs-validation" novalidate
          method="POST"
          action="${pageContext.request.contextPath}/EditCar">

        <div class="row mb-3">
            <div class="col">
                <label for="license_plate">License Plate</label>
                <input type="text" class="form-control" id="license_plate"
                       name="licensePlate" placeholder="" value="${car.licensePlate}" required>
                <div class="invalid-feedback">License Plate is required.</div>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Parking Spot</label>
                <input type="text" class="form-control"
                       name="parkingSpot" placeholder="" value="${car.parkingSpot}" required>
                <div class="invalid-feedback">Parking Spot is required.</div>
            </div>
        </div>

        <div class="row mb-3">
            <div class="col">
                <label class="form-label">Owner</label>
                <select class="form-select" name="owner_id" required>
                    <option value="">Choose...</option>

                    <c:forEach var="user" items="${users}" varStatus="status">
                        <option value="${user.id}" ${car.ownerName eq user.username ? 'selected':' '}>
                                ${user.username}
                        </option>
                    </c:forEach>

                </select>
                <div class="invalid-feedback">Please select an owner.</div>
            </div>
        </div>
        <input type="hidden" name="car_id" value="${car.id}" />
        <button class="btn btn-primary" type="submit">Save</button>
    </form>

</t:pageTemplate>
