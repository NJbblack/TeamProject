AOS.init({
  duration: 600,
  delay: 200,
});

const swiper = new Swiper(".discount-banner", {
  slidesPerView: 3,
  spaceBetween: 20,
  loop: true,
  navigation: {
    nextEl: ".discount-banner .btn--next",
    prevEl: ".discount-banner .btn--prev",
  },
});

const btnTop = document.querySelector(".btn-top");
btnTop.addEventListener("click", function () {
  window.scrollTo({ top: 0, behavior: "smooth" });
});

const header = document.querySelector(".header");
window.addEventListener("scroll", function (e) {
  if (window.scrollY > 0) {
    header.classList.add("on");
    btnTop.classList.add("on");
  } else {
    header.classList.remove("on");
    btnTop.classList.remove("on");
  }
});

$(".sessionCheck").on("click", function () {
  if (session == null) {
    alert("You need to login to utilize this function.");
    return false;
  }
});

$(".vipCheck").on("click", function () {
  if (session.userRank < 2) {
    alert("You need to be a vip to utilize this function");
    return false;
  }
});

if (msg) {
  memoModal.show();
}

$("#btnDeleteAjax").on("click", function (e) {
  console.log("클릭");
  //e.preventDefault();
  isState = "delete";
  $("#deleteModal .modal-title").text("Enter password to delete your account.");
  deleteModal.show();
  return false;
});

$("#btnDeleteBoardAjax").on("click", function (e) {
  //e.preventDefault();
  isState = "delete";
  $("#deleteBoardModal .modal-title").text(
    "Enter password to delete your board content."
  );
  deleteBoardModal.show();
  return false;
});

$("#btnNoticeDeleteBoardAjax").on("click", function (e) {
  //e.preventDefault();
  isState = "delete";
  $("#deleteVipBoardModal .modal-title").text(
    "Enter password to delete your board content (vip)"
  );
  deleteVipBoardModal.show();
  return false;
});

$("#btnDeleteConfirm").on("click", function (e) {
  $.ajax({
    url: "/member/memberDeleteProcess", // 해당 함수 필요 (responsebody)
    data: { no: session.no, userPw: $("#userPw_check").val() },
    type: "POST",
    success: function (response) {
      console.log(response);
      if (response.msg === "ok") {
        alert("Your account has been successfully deleted!");
        setTimeout(function () {
          location.href = "/";
        }, 500);
      } else {
        alert("Wrong password. Try again.");
        location.reload();
      }
    },
  });
});

//////////////////////info 화면에서 버튼 클릭시 현재 예약 정보를 뿌리는 기능
$("#btnCurrentReserv").on("click", function (e) {
  const sendData = {
    no: $("#loggedMemberNo").val(),
  };
  $.ajax({
    url: "/reservation/getAllReserv",
    data: sendData,
    type: "POST",
    success: function (response) {
      console.log(response);
      let tempHtml = "";
      $.each(response, function (idx, item) {
        tempHtml += `
        <form
        action="/reservation/getDetailReserv"
        method="post"
        >
        <input type="hidden" name="reservNo" value=${item.reservNo}>
        <button href="" class="card cardReserv btn-warning w-100" style="height:26vh;">
          <div class="card-text"><strong><small>Reservation No: ${item.reservNo}</small></strong></div>
          <div class="card-body package_result row">
            <div class="col-2">
            <img
            src="http://localhost:9091/upload/${item.reservImg}"
            alt="${item.reservPkg}" style="width:15vw; height: 20vh; padding:0; top:4vh; left:5px; position:absolute;"
            />
            </div>
            <div class="col-3" style="text-align:left; margin-top:-20px; margin-left:45px; ">
              <h4 class="card-title"><strong>${item.reservPkg}</strong></h4>
              <div>
              <span style="font-size:13px;">Reservation Name: </span><br><strong style="font-size:16px;">${item.reservName}</strong>
              </div>
              <div>
              <span style="font-size:13px;">Departure Date: </span><br><strong style="font-size:16px;">${item.reservDepart}</strong>
              </div>
              <div>
              <span style="font-size:13px;">Return Date: </span><br><strong style="font-size:16px;">${item.reservReturn}</strong>
              </div>
            </div>
            <div class="col" style="text-align:left; margin-top:0; margin-left:-110px;  margin-bottom:40px;">
            <div style="position: absolute; top:0; right:5px;">
            <span style="font-size:13px;">Reservation Date: &ensp;</span><strong>20${item.reservRegDate}</strong>
            </div>
            <span style="font-size:12px; line-height:0; margin:0;">${item.pkgContents}</span>
            </div>
          </div>
        </button>
        </form>
        `;
      });
      $(".result").html(tempHtml);
    },
  });
});

//////////////////////info 화면에서 Past Reservation클릭시 과거 예약 정보를 뿌리는 기능
$("#btnPastReserv").on("click", function (e) {
  const sendData = {
    no: $("#loggedMemberNo").val(),
  };
  $.ajax({
    url: "/reservation/getPastReserv",
    data: sendData,
    type: "POST",
    success: function (response) {
      console.log(response);
      let tempHtml = "";
      $.each(response, function (idx, item) {
        tempHtml += `
        <form
        action="/reservation/getDetailReserv"
        method="post"
        >
        <input type="hidden" name="reservNo" value=${item.reservNo}>
        <button href="" class="card cardReserv btn-secondary w-100" style="height:26vh;" disabled>
          <div class="card-text"><strong><small>Reservation No: ${item.reservNo}</small></strong>ㅤㅤㅤ&nbsp;&nbsp;&nbsp;
          <a href="/reservation/review?reservNo=${item.reservNo}&selectedPkgNo=${item.selectedPkgNo}" class="btn btn-dark btnReview" 
          style="padding:3px; padding-bottom:8px; opacity:${item.reviewAvailable}; position:absolute; top:0vh; right:11.5vw">Write Review</a>
          </div>
          <div class="card-body package_result row">
            <div class="col-2">
            <img
            src="http://localhost:9091/upload/${item.reservImg}"
            alt="${item.reservPkg}" style="width:15vw; height: 20vh; padding:0; top:4vh; left:5px; position:absolute;"
            />
            </div>
            <div class="col-3" style="text-align:left; margin-top:-20px; margin-left:45px; ">
              <h4 class="card-title"><strong>${item.reservPkg}</strong></h4>
              <div>
              <span style="font-size:13px;">Reservation Name: </span><br><strong style="font-size:16px;">${item.reservName}</strong>
              </div>
              <div>
              <span style="font-size:13px;">Departure Date: </span><br><strong style="font-size:16px;">${item.reservDepart}</strong>
              </div>
              <div>
              <span style="font-size:13px;">Return Date: </span><br><strong style="font-size:16px;">${item.reservReturn}</strong>
              </div>
            </div>
            <div class="col" style="text-align:left; margin-top:0; margin-left:-110px;  margin-bottom:40px;">
            <div style="position: absolute; top:0; right:5px;">
            <span style="font-size:13px;">Reservation Date: &ensp;</span><strong>20${item.reservRegDate}</strong>
            </div>
            <span style="font-size:12px; line-height:0; margin:0;">${item.pkgContents}</span>
            </div>
          </div>
        </button>
        </form>
        `;
      });
      $(".result").html(tempHtml);
    },
  });
});

//////////////////////info 화면에서 Canceled Reservation클릭시 취소된 예약 정보를 뿌리는 기능
$("#btnCanceledReserv").on("click", function (e) {
  const sendData = {
    no: $("#loggedMemberNo").val(),
  };
  $.ajax({
    url: "/reservation/getCanceledReserv",
    data: sendData,
    type: "POST",
    success: function (response) {
      console.log(response);
      let tempHtml = "";
      $.each(response, function (idx, item) {
        tempHtml += `      
        <form
        action="/reservation/getDetailReserv"
        method="post"
        >
        <input type="hidden" name="reservNo" value=${item.reservNo}>
        <button href="" class="card cardReserv btn-danger w-100" style="height:26vh;" disabled>
        <div class="card-text"><strong><small>Reservation No: ${item.reservNo}</small></strong>ㅤㅤㅤ&nbsp;&nbsp;&nbsp;
        </div>
        <div class="card-body package_result row">
          <div class="col-2">
          <img
          src="http://localhost:9091/upload/${item.reservImg}"
          alt="${item.reservPkg}" style="width:15vw; height: 20vh; padding:0; top:4vh; left:5px; position:absolute;"
          />
          </div>
          <div class="col-3" style="text-align:left; margin-top:-20px; margin-left:45px; ">
            <h4 class="card-title"><strong>${item.reservPkg}</strong></h4>
            <div>
            <span style="font-size:13px;">Reservation Name: </span><br><strong style="font-size:16px;">${item.reservName}</strong>
            </div>
            <div>
            <span style="font-size:13px;">Departure Date: </span><br><strong style="font-size:16px;">${item.reservDepart}</strong>
            </div>
            <div>
            <span style="font-size:13px;">Return Date: </span><br><strong style="font-size:16px;">${item.reservReturn}</strong>
            </div>
          </div>
          <div class="col" style="text-align:left; margin-top:0; margin-left:-110px;  margin-bottom:40px;">
          <div style="position: absolute; top:0; right:5px;">
          <span style="font-size:13px;">Reservation Date: &ensp;</span><strong>20${item.reservRegDate}</strong>
          </div>
          <span style="font-size:12px; line-height:0; margin:0;">${item.pkgContents}</span>
          </div>
        </div>
        </button>
        </form>`;
      });
      $(".result").html(tempHtml);
    },
  });
});

//////////////////////info 화면에서 버튼 클릭시 현재 예약 변경 요청 정보를 뿌리는 기능
$("#btnReqOngoing").on("click", function (e) {
  const sendData = {
    no: $("#loggedMemberNo").val(),
  };
  $.ajax({
    url: "/reservation/getAllRequest",
    data: sendData,
    type: "POST",
    success: function (response) {
      console.log(response);
      let tempHtml = "";
      $.each(response, function (idx, item) {
        tempHtml += `      <div class="resultReq">
        <form           
        action="/reservation/requestview"
        method="post"
        >
        <input type="hidden" name="reqNo" value=${item.reqNo}>
        <input type="hidden" name="reservNo" value=${item.reservNo}>
        <button href="" class="card cardReserv btn-outline-primary w-100" style="height:26vh;">
        <div class="card-text"><strong><small>Reservation No: ${item.reservNo}</small></strong>ㅤㅤㅤ&nbsp;&nbsp;&nbsp;
        <a href="/reservation/review?reservNo=${item.reservNo}&selectedPkgNo=${item.selectedPkgNo}" class="btn btn-dark btnReview" style="padding:3px; padding-bottom:8px; opacity:${item.reviewAvailable};">Write Review</a>
        </div>
        <div class="card-body package_result row">
          <div class="col-2">
          <img
          src="http://localhost:9091/upload/${item.reservImg}"
          alt="${item.reservPkg}" style="width:15vw; height: 20vh; padding:0; top:4vh; left:5px; position:absolute;"
          />
          </div>
          <div class="col-3" style="text-align:left; margin-top:-20px; margin-left:45px; ">
            <h4 class="card-title"><strong>${item.reservPkg}</strong></h4>
            <div>
            <span style="font-size:13px;">Reservation Name: </span><br><strong style="font-size:16px;">${item.reservName}</strong>
            </div>
            <div>
            <span style="font-size:13px;">Departure Date: </span><br><strong style="font-size:16px;">${item.reservDepart}</strong>
            </div>
            <div>
            <span style="font-size:13px;">Return Date: </span><br><strong style="font-size:16px;">${item.reservReturn}</strong>
            </div>
          </div>
          <div class="col" style="text-align:left; margin-top:0; margin-left:-110px;  margin-bottom:40px;">
          <div style="position: absolute; top:0; right:5px;">
          <span style="font-size:13px;">Reservation Date: &ensp;</span><strong>20${item.reservRegDate}</strong>
          </div>
          <span style="font-size:12px; line-height:0; margin:0;">${item.pkgContents}</span>
          </div>
        </div>
        </button>
        </form>`;
      });
      $(".resultReq").html(tempHtml);
    },
  });
});

//////////////////////info 화면에서 버튼 클릭시 종료된 예약 변경 요청 정보를 뿌리는 기능
$("#btnReqClosed").on("click", function (e) {
  const sendData = {
    no: $("#loggedMemberNo").val(),
  };
  $.ajax({
    url: "/reservation/getClosedRequest",
    data: sendData,
    type: "POST",
    success: function (response) {
      console.log(response);
      let tempHtml = "";
      $.each(response, function (idx, item) {
        tempHtml += `      <div class="resultReq">
        <form           
        action="/reservation/requestview"
        method="post"
        >
        <input type="hidden" name="reqNo" value=${item.reqNo}>
        <input type="hidden" name="reservNo" value=${item.reservNo}>
        <button href="" class="card cardReserv btn-outline-secondary w-100" style="height:26vh;" disabled>
          <div class="card-text"><strong><small>Request No: ${item.reqNo}</small></strong></div>
          <div class="card-body package_result row w-100">
            <div class="col-3 reqPic">
            <img
            src="http://localhost:9091/upload/${item.requestImg}"
            alt="${item.requestImg}" style="width:15vw; height: 20vh; padding:0; top:4vh; left:5px; position:absolute;"
            />
            </div>
            <div class="col-3 reqInfo" style="text-align:left;">
              <h4 class="card-title"><strong>${item.pkgName}</strong></h4>
              <div>
              <span style="font-size:13px;">Reservation Name: </span><br><strong style="font-size:16px;">${item.reservName}</strong>
              </div>
              <div>
              <span style="font-size:13px;">Departure Date: </span><br><strong style="font-size:16px;">${item.pkgDepart}</strong>
              </div>
              <div>
              <span style="font-size:13px;">Return Date: </span><br><strong style="font-size:16px;">${item.pkgReturn}</strong>
              </div>
            </div>
            <div class="col-6 reqDetail" style="text-align:left;">
            <div style="position: absolute; top:0; right:5px;">
            <span style="font-size:13px;">Request Date: &ensp;</span><strong>20${item.reqDate}</strong>
            </div>
            <div>
              <span style="font-size:13px;">Request Title: </span><br><h3>${item.reqTitle}</h3>
              </div>
            <span style="font-size:12px; line-height:0; margin:0;">${item.reqContents}</span>
            </div>
          </div>
        </button>
        </form>`;
      });
      $(".resultReq").html(tempHtml);
    },
  });
});

// $(".result").on("click", ".card", function (e) {
//   const test = $(this).parent;
//   const sendData = {
//     reservNo: $(".result .card").data("no"),
//   };
//   console.log(sendData);
//   $.ajax({
//     url: "/reservation/reservview",
//     data: sendData,
//     type: "POST",
//     success: function (response) {
//       console.log(response);
//     },
//   });
// });

$("#btnBoardDeleteConfirm").on("click", function (e) {
  console.log("클릭");
  $.ajax({
    url: "/board/faqBoardDeleteProcess", // 해당 함수 필요 (responsebody)
    data: {
      faqNo: $("#faqNoForDelete").val(),
      userPw: $("#userPw_faqCheck").val(),
    },
    type: "POST",
    success: function (response) {
      console.log(response);
      if (response.msg === "ok") {
        alert("Your content has been successfully deleted!");
        setTimeout(function () {
          location.href = "/board/faqboard";
        }, 50);
      } else {
        alert("Wrong password. Try again.");
        location.reload();
      }
    },
  });
});

$("#btnNoticeBoardDeleteConfirm").on("click", function (e) {
  $.ajax({
    url: "/board/noticeBoardDeleteProcess", // 해당 함수 필요 (responsebody)
    data: {
      noticeNo: $("#noticeNoForDelete").val(),
      userPw: $("#userPw_noticeCheck").val(),
    },
    type: "POST",
    success: function (response) {
      console.log(response);
      if (response.msg === "ok") {
        alert("Your content has been successfully deleted!");
        setTimeout(function () {
          location.href = "/board/noticeboard";
        }, 50);
      } else {
        alert("Wrong password. Try again.");
        location.reload();
      }
    },
  });
});
$("#btnDeleteQnaBoardAjax").on("click", function (e) {
  //e.preventDefault();
  isState = "delete";
  $("#deleteQnaBoardModal .modal-title").text(
    "Enter password to delete your board content."
  );
  deleteQnaBoardModal.show();
  return false;
});

$("#btnQnaBoardDeleteConfirm").on("click", function (e) {
  console.log("클릭");
  $.ajax({
    url: "/board/qnaBoardDeleteProcess", // 해당 함수 필요 (responsebody)
    data: {
      qnaNo: $("#qnaNoForDelete").val(),
      userPw: $("#userPw_qnaCheck").val(),
    },
    type: "POST",
    success: function (response) {
      console.log(response);
      if (response.msg === "ok") {
        alert("Your content has been successfully deleted!");
        setTimeout(function () {
          location.href = "/board/qnaboard";
        }, 50);
      } else {
        alert("Wrong password. Try again.");
        location.reload();
      }
    },
  });
});

$("#pkgContainer div:nth-child(1)").attr("data-aos-delay", "1500");
$("#pkgContainer div:nth-child(2)").attr("data-aos-delay", "1000");
$("#pkgContainer div:nth-child(3)").attr("data-aos-delay", "500");
$("#pkgContainer div:nth-child(4)").attr("data-aos-delay", "0");
$("#pkgContainer div:nth-child(5)").attr("data-aos-delay", "1500");
$("#pkgContainer div:nth-child(6)").attr("data-aos-delay", "1000");
$("#pkgContainer div:nth-child(7)").attr("data-aos-delay", "500");
$("#pkgContainer div:nth-child(8)").attr("data-aos-delay", "0");
$("#pkgContainer div:nth-child(9)").attr("data-aos-delay", "1500");
$("#pkgContainer div:nth-child(10)").attr("data-aos-delay", "1000");
$("#pkgContainer div:nth-child(11)").attr("data-aos-delay", "500");
$("#pkgContainer div:nth-child(12)").attr("data-aos-delay", "0");

$("#pkgSlide li:nth-child(1)").attr("data-aos-delay", "1200");
$("#pkgSlide li:nth-child(2)").attr("data-aos-delay", "1000");
$("#pkgSlide li:nth-child(3)").attr("data-aos-delay", "800");
$("#pkgSlide li:nth-child(4)").attr("data-aos-delay", "600");
$("#pkgSlide li:nth-child(5)").attr("data-aos-delay", "400");
$("#pkgSlide li:nth-child(6)").attr("data-aos-delay", "600");
$("#pkgSlide li:nth-child(7)").attr("data-aos-delay", "800");
$("#pkgSlide li:nth-child(8)").attr("data-aos-delay", "1000");
$("#pkgSlide li:nth-child(9)").attr("data-aos-delay", "1200");
$("#pkgSlide li:nth-child(10)").attr("data-aos-delay", "1400");
$("#pkgSlide li:nth-child(11)").attr("data-aos-delay", "1600");
$("#pkgSlide li:nth-child(12)").attr("data-aos-delay", "1800");

const picker01 = new Lightpick({
  field: document.querySelector("#datepicker01"),
  format: "YYYY/MM/DD",
  defaultDate: "01/01/01",
});
const picker02 = new Lightpick({
  field: document.querySelector("#datepicker02"),
  format: "YYYY/MM/DD",
  defaultDate: "01/01/01",
});
const picker03 = new Lightpick({
  field: document.querySelector("#datepicker03"),
  format: "YYYY/MM/DD",
  defaultDate: "01/01/01",
});

/////////////////////////////////////////////////////////////////////
/*  이 함수 작동하는 거 확인함 (멤버 삭제 - info에서)
    @PostMapping("/deleteProcess")
    @ResponseBody
    public Map<String, Object> deleteProcess(
    MemberDto memberDto,
    HttpServletRequest request,
    RedirectAttributes redirectAttributes) throws IOException  {
    int result = memberService.deleteMember(memberDto);
    System.out.println(result);
    Map<String, Object> sendJson = new HashMap<>();
    if (result > 0) {
      HttpSession session = request.getSession();
      session.removeAttribute("loggedMember");
      sendJson.put("msg", "ok");
    } else {
      sendJson.put("msg", "fail");
    }
    return sendJson;
    }
*/
