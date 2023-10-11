console.log(bnoVal);
async function postCommentToServer(cmtData)
{
    try {
        const url="/comment/post";
        const config={
            method:"post",
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtData)
        };

        const resp=await fetch(url,config);
        const result=await resp.text(); //isOk
        return result;
    } catch (error) {
        console.log(error);
    }
   
}
 document.getElementById('cmtPostBtn').addEventListener('click',()=>{
        const cmtText=document.getElementById('cmtText').value;
        const cmtWriter=document.getElementById('cmtWriter').innerText;
        if(cmtText==""||cmtText==null){
            alert("댓글을 입력해주세요.");
            document.getElementById('cmtText').focus();
            return false;
        }
        else
        {
            let cmtData={
                bno:bnoVal,
                writer:cmtWriter,
                content:cmtText
            }
            console.log(cmtData);
            postCommentToServer(cmtData).then(result=>{
                console.log(result);
                //isOk 확인
                if(result==1)
                {
                    alert('댓글등록 성공~!!');
                    //화면에 뿌리기
                    getCommentList(bnoVal);
                }
            })
        }
    })
async function spreadCommentListFromServer(bno)
{
    try {
        const resp=await fetch('/comment/'+bno);
        const result=await resp.json();
        return result;
    } catch (error) {
        console.log(error);
    }
}

 function getCommentList(bno)
{
    spreadCommentListFromServer(bno).then(result=>{
        console.log(result);
        //화면에 뿌리기
        // <li>
		// 	<div>
		// 		<div>writer</div>
		// 		Content
		// 	</div>
		// 	<span>reg_date</span>
		// </li>
        let div=document.getElementById('cmtListArea');
        if(result.length>0)
        {
            div.innerHTML="";
            for(let i=0;i<result.length;i++)
            {
                let str=`<li data-cno="${result[i].cno}" data-writer="${result[i].writer}">`;
                str+=`<div>`;
                str+=`<div>작성자:${result[i].writer}</div>`;
                str+=`내용:<input type="text" id="cmtTextMod" value="${result[i].content}"></div>`;
                str+=`<span>작성일:${result[i].reg_date}</span>`;
                str+=`<button type="button" class="modBtn">수정</button>`
                str+=`<button type="button" class="delBtn">삭제</button>`
                str+="</li>";
                div.innerHTML += str;
            }
        }
        else{
            let li=`<li>Comment List Empty</li>`;
            div.innerHTML=li;
        }

    })
}

async function editCommentToServer(cmtModData)  //cno만 받아와서 보내도됨
{
    try {
        const url='/comment/'+cmtModData.cno;
        const config={
            method:'put',
            headers:{
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtModData)
        };
        const resp=await fetch(url,config);
        const result=await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function removeCommentToServer(cno)
{
    try {
        const url='/comment/'+cno;
        const config={
            method:'delete'
        };
        const resp=await fetch(url,config);
        const result=await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

document.addEventListener('click',(e)=>{
    console.log(e);
    if(e.target.classList.contains('modBtn'))
    {
        //수정작업
        console.log('수정버튼 클릭~!!');
        //내가 선택한 타겟과 가장 가까운 li 찾기
        let li=e.target.closest('li');
        let cnoVal=li.dataset.cno;
        let cmtWriter=li.dataset.writer;
        let textContent=li.querySelector('#cmtTextMod').value;
        console.log("cno/content/writer"+cnoVal+"   "+textContent+"   "+cmtWriter);

        let cmtModData={
            cno:cnoVal,
            writer:cmtWriter,
            content:textContent
        };
        console.log(cnoVal);
        console.log(cmtWriter);
        console.log(textContent);
        console.log(cmtModData);
        //서버연결
        editCommentToServer(cmtModData).then(result=>{
            if(result==1){
                alert('댓글 수정 성공~!!');
            }
            else if(result==2){
                alert("id가 다릅니다.")
            }
            getCommentList(bnoVal);
        })
    }
    else if(e.target.classList.contains('delBtn'))
    {
        console.log('삭제버튼 클릭~!!');
        let li=e.target.closest('li');
        let cnoVal=li.dataset.cno;
        //삭제작업
        removeCommentToServer(cnoVal).then(result=>{
            if(result==1){
                alert('댓글 삭제 성공~!!')
            }
            getCommentList(bnoVal);
        })
    }

})