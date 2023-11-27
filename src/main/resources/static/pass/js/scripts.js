<!DOCTYPE html>
<html lang="ko" xmlns:th="http://www.thymeleaf.org">
    <th:block th:replace="/admin/header :: header"></th:block>
<body id="page-top">
<!-- Page Wrapper -->
<div id="wrapper">
    <th:block th:replace="/admin/sidebar :: sidebar"></th:block>
    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">
        <!-- Main Content -->
        <div id="content">
            <th:block th:replace="/admin/topbar :: topbar"></th:block>
            <!-- Begin Page Content -->
            <div class="container-fluid">
                <!-- Page Heading -->
                <h1 class="h3 mb-1 text-gray-800">Passes</h1>
                <p class="mb-4">다수의 회원들에게 이용권을 일괄 지급을 예약할 수 있습니다. 이용권은 시작 시간 1일 전 회원들에게 지급됩니다.</p>
                <!-- Content Row -->
                <div class="row">
                    <!-- Grow In Utility -->
                    <div class="col-lg-8">
                        <div class="card position-relative">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">이용권 일괄 지급 목록</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                        <tr>
                                            <th>회원 그룹 ID</th>
                                            <th>상태</th>
                                            <th>횟수</th>
                                            <th>시작 일시</th>
                                            <th>종료 일시</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr th:each="bulkPass : ${bulkPasses}" th:classappend="${bulkPass.getStatus().name() == 'COMPLETED' ? 'table-active' : ''}">
                                            <td th:text="${bulkPass.getUserGroupId()}"></td>
                                            <td th:if="${bulkPass.getStatus().name() == 'COMPLETED'}">지급 완료</td>
                                            <td th:unless="${bulkPass.getStatus().name() == 'COMPLETED'}">지급 전</td>
                                            <td th:text="${bulkPass.getCount()}"></td>
                                            <td th:text="${#temporals.format(bulkPass.getStartedAt(), 'yyyy-MM-dd HH:mm')}"></td>
                                            <td th:text="${#temporals.format(bulkPass.getEndedAt(), 'yyyy-MM-dd HH:mm')}"></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-lg-4">
                        <div class="card position-relative full-height">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">이용권 일괄 지급 등록</h6>
                            </div>
                            <div class="card-body">
                                <form action="#" th:action="@{/admin/bulk-pass}" th:object="${request}" method="post">
                                    <div class="small mb-1">패키지</div>
                                    <select class="form-control mb-3" th:field="*{packageSeq}">
                                        <option value="">등록된 패키지를 선택해주세요.</option>
                                        <option th:each="package : ${packages}"
                                                th:value="${package.getPackageSeq()}"
                                                th:text="${package.getPackageName()}" >
                                        </option>
                                    </select>

                                    <div class="small mb-1">회원 그룹 ID</div>
                                    <select class="form-control mb-3" th:field="*{userGroupId}">
                                        <option value="">등록된 회원 그룹을 선택해주세요.</option>
                                        <option th:each="userGroupId : ${userGroupIds}"
                                                th:value="${userGroupId}"
                                                th:text="${userGroupId}">
                                        </option>
                                    </select>

                                    <div class="small mb-1">시작 일시</div>
                                    <input class="form-control mb-5" type="text" placeholder="2022-09-01 00:00" th:field="*{startedAt}" />
                                    <div class="d-flex align-items-end flex-column">
                                        <input class="btn btn-primary" type="submit" value="+ 등록">
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Fade In Utility -->

    </div>

</div>

</div>
<!-- /.container-fluid -->

</div>
<!-- End of Main Content -->
<th:block th:replace="/admin/footer :: footer"></th:block>
</div>
<!-- End of Content Wrapper -->

</div>
<!-- End of Page Wrapper -->
<th:block th:replace="/admin/script :: script"></th:block>

</body>
</html>