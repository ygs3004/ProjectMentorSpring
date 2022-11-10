// let weeklyList = new Array('mentorRoom.getWeeklyList}');
//
// for(let weeklys in weeklyList){
//     $("input:checkbox[value=weeklys]).prop("checked", true);
// };
// window.onload = function(){
//     // for(let btns in document.getElementById("delev")){
//     //     btns.addEventListener("click", delAct);
//     // }
//
//     // let weeklyList = new Array('mentorRoom.getWeeklyList');
//     //
//     // for(let weeklys in weeklyList){
//     //     $('[value=weeklys]').prop("checked", true);
//     // };
// }
// window.onload = function(){
//     let weeklyList = document.getElementById("weeklyList");
//     for(let list in weeklyList){
//         console.log(list.valueOf());
//     }
// }
let list = "";
window.onload =(function() {
    $.ajax({
        url: "/MentorRoom/getRoomInfo",
        type: "get",
        dataType: "json",
        contentType: "application/json",
        success: function(mentorRoom) {
            console.log(mentorRoom);
            for(let weeks in mentorRoom.weeklyList){

            }

            let strinfo = JSON.stringify(mentorRoom);
            console.log("stringify" + strinfo);

            let jsoninfo = JSON.parse(strinfo);
            console.log("parse" + jsoninfo);

            // alert("성공");
            //
            // console.log("mentorRoom"+mentorRoom);
            //

            //

        },
        error: function (errorThrown) {
            alert(errorThrown.statusText);
        }
    });
});

$(function() {
    $('input[name="studyPeriod"]').daterangepicker({
        autoUpdateInput: false,
        minDate: new Date(),
        // changeMonth: true,
        locale: {
            format: "YYYY-MM-DD",
            applyLabel :"선택",
            cancelLabel:"취소",
            daysOfWeek:['일','월','화','수','목','금','토'],
            monthNames: ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        }
    });
    $('input[name="studyPeriod"]').on('apply.daterangepicker', function(ev, picker) {
        $(this).val(picker.startDate.format('YYYY/MM/DD') + ' - ' + picker.endDate.format('YYYY/MM/DD'));
    });
    $('input[name="studyPeriod"]').on('cancel.daterangepicker', function(ev, picker) {
        $(this).val('');
    });
});

//career
function addCreerList() {
    const addstr = document.getElementById('addCareer');
    const li = document.createElement('li');
    const input = document.createElement('input');
    const del = document.createElement('a');

    //<input type="hidden" name="carrer" value=add.value>
    input.setAttribute('type',"hidden");
    input.setAttribute('name','career');
    input.setAttribute('value',addstr.value);

    //<a id="delByn">삭제</a>
    del.append("삭제");
    del.setAttribute('id','delBtn');
    del.addEventListener("click", delAct);

    //<p>str<input type="hidden" name="carrer" value=add.value><a>삭제</a></p>
    const str = document.createTextNode(addstr.value);
    li.appendChild(str);
    li.append(" ");
    li.appendChild(input);

    document.getElementById('careerList').appendChild(li).appendChild(del);
    addstr.value=""; //추가 후 input 비우기
};

function delAct(e) {
    const event = e || window.event;
    const targetElement = event.target.parentElement;
    targetElement.remove();
}

//입력값이 잘못된 경우 false를 리턴.
function doCheck(){
    let enWeekly = document.querySelectorAll("input[type=checkbox][name=studyWeekly]:checked"); //체크된 요일리스트 불러오기
    if(document.getElementById("title").value.length == 0){
        alert('스터디 이름을 입력해주세요.')
        document.getElementById("title").focus();
        return false;
    }else if(document.getElementById("studyPeriod").value.length == 0){
        alert('스터디 기간을 입력해주세요.');
        document.getElementById("studyPeriod").focus();
        return false;
    }
    //요일이 선택되었는지 확인
    else if(enWeekly.length == 0 | enWeekly==null){
        alert('요일을 선택해 주세요.');
        return false;
    }

    else if(document.getElementById('studyTimeStart').value.length == 0){
        alert('스터디 시작 시간을 입력해주세요.');
        document.getElementById('studyTimeStart').focus();
        return false;
    }

    else if(document.getElementById('studyTimeEnd').value.length == 0){
        alert('스터디 종료 시간을 입력해주세요.');
        document.getElementById('studyTimeEnd').focus();
        return false;
    }

    else if(document.getElementById('studyTimeEnd').value < document.getElementById('studyTimeStart').value){
        alert('스터디 종료 시간은 스터디 시작 시간보다 빠를 수 없습니다.');
        document.getElementById('studyTimeStart').focus();
        return false;
    }

    else if(document.getElementById("capacity").value.length == 0){
        alert('모집인원을 입력해주세요.');
        document.getElementById("capacity").focus();
        return false;
    }

    else if(document.getElementById("capacity").value < document.getElementById("nowCapacity").value){
        alert('현재 모집된 인원이 수정할 모집인원보다 많습니다.\r 수정할 모집인원은 모집된 인원보다 많아야합니다.');
        document.getElementById("capacity").focus();
        return false;
    }

    else if(document.getElementById("content").value.length == 0){
        alert('상세설명을 입력해주세요.');
        document.getElementById("content").focus();
        return false;
    }else {
        return lastCheck();
    }
}

function lastCheck() {
    if (confirm("스터디 정보를 수정하시겠습니까?") == true){    //확인
        return true;
    }else{   //취소
        return false;
    }
}

function delCheck() {
    if (confirm("스터디를 삭제하시겠습니까?") == true){ //확인
        return location.href='/MentorRoom/delRoom';
    }else{
        return false;
    }
}