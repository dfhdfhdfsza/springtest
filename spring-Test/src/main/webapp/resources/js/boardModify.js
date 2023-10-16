async function editFileToServer(uuidVal)
{
    try {
        const url='/board/file/'+uuidVal;
        const config={
            method:'delete',
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
    if(e.target.classList.contains('file-x'))
    {
        console.log('파일삭제버튼 클릭');
        let button=e.target.closest('button');
        console.log('div:'+button);
        let uuidVal=button.dataset.uuid;
        console.log('uuid:'+uuidVal);

        
        editFileToServer(uuidVal).then(result=>{
            if(result==1){
                alert('삭제 성공!');
                e.target.closest('li').remove(); //li삭제
                location.reload();  //새로고침
            }
            else{
                alert('삭제 실패');
            }
        })
    }
})
