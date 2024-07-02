const url ="http://localhost:8080/api/products/purchaselist";


function sessionCurrent() {
  axios
  .get("http://localhost:8080/user/current", {withCredentials : true})
  .then((response)=>{
    console.log("데이터:",response.data);
    if(response.status == 200){
      //const userId = response.data;
      const userId = response.data.userId;
      const authority = response.data.authority[0].authority;
      let cartItems = JSON.parse(localStorage.getItem(userId));
      if(cartItems){

        displayCart(cartItems) //게임의 배열

        const data = cartItems.map((game)=>{
          //Purchase 객체를 만들어서 리턴
          //return { game:game, user:{userId:userId} };
          return { game:game, user:{userId:userId 
                  , authority:{authorityName:authority}} };
        })
        document.querySelector(".purchaseBtn")
        .addEventListener("click",()=>{
          if(confirm("진짜 구매하시겠습니까?")) {
            axios
            .post(url, data, {withCredentials : true})
            .then((response)=>{
              console.log("데이터:",response.data);
              localStorage.removeItem(userId);
              window.location.reload(); // 이 페이지가 다시 리로드 되서 새로고침되는것.
            })
            .catch((error)=>{
              console.log("에러발생", error);
            });
          }
        })
      }
    }
  })
  .catch((error)=>{
    console.log("에러 발생:", error);
    alert("로그인해주세요.");
  });
  
}

function displayCart(games){
  const tbody = document.querySelector(".cart-body");
  let totalPrice = 0;
  games.forEach((data)=>{  //반복문
    //태그요소 생성
    const tr = document.createElement("tr");
    const imgtd = document.createElement("td");
    const title = document.createElement("td");
    const genre = document.createElement("td");
    const price = document.createElement("td");
    const img = document.createElement("img");
    //클래스이름 생성
    imgtd.classList.add("imgtd");
    img.classList.add("image");
    //태그속성 추가
    img.src =  data.image;
    title.textContent =  data.title;
    genre.textContent =  data.genre;
    price.textContent = data.price + "원";
    //appendChild 부모자식 위치 설정 - 가장아래에 사용한다.
    imgtd.appendChild(img);
    tr.appendChild(imgtd);
    tr.appendChild(title);
    tr.appendChild(genre);
    tr.appendChild(price);
    tbody.appendChild(tr);

    totalPrice = totalPrice + data.price;
   })
   document.querySelector(".totalprice").textContent = "총 "+ totalPrice + "원";
}

//페이지 로딩시에 즉시 세션여부 확인
sessionCurrent()