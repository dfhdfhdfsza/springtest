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
                }
            })
        }
    })
}