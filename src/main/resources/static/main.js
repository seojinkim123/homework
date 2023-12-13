const call = document.querySelector('#call');

const myImage=document.querySelector('#myImage');

// call.addEventListener('click',()=>{
//     const xhr=new XMLHttpRequest();
//     xhr.open('GET','http://localhost:8080/image');
//     xhr.send();
    
//     xhr.onload=()=>{

//             myImage.src=xhr.response;
//             myImage.style.width="100px";

//     }



// })

document.addEventListener('DOMContentLoaded',()=>{
       
    const xhr=new XMLHttpRequest();
    xhr.open('GET','http://localhost:8080/image');
    xhr.send();
        
    xhr.onload=()=>{
    
        myImage.src=xhr.response;
        myImage.style.width="100px";
    
    }
    


})