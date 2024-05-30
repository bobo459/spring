const urlParams = new URLSearchParams(window.location.search);  // 주소창에서 파라미터를 읽어오는 친구
const id = urlParams.get("id");
console.log("Game ID : ", id);

const url = "http://localhost:8080/products/" + id;

axios
.get(url)
.then((response)=>{
  console.log("데이터 : ",response);
  displaySingleProducts(response.data);
})
.catch((error)=>{
  console.log("에러 발생 : ",error);
});

function displaySingleProducts(data){ //필수로 알아야하는 기본구간(동적으로 만드는 구조)
  const product = document.querySelector(".product");

  //태크 요소 생성
  const game = document.createElement("div");
  const img = document.createElement("img");
  const title = document.createElement("p");
  const genre = document.createElement("p");
  const price = document.createElement("p");
  const text = document.createElement("p");
  const lowBox = document.createElement("div");
  const left = document.createElement("div");
  const right = document.createElement("div");
  const carBtn = document.createElement("div");

  //클래스 이름 생성
  img.src = data.image;
  game.classList.add("game");
  img.classList.add("image");
  lowBox.classList.add("low-box");
  carBtn.classList.add("cartBtn");
  
  //태그속성 추가
  img.src = data.image;
  title.textContent = "게임 타이틀 : "+ data.title;
  genre.textContent = "게임 장르 : " +data.genre;
  price.textContent = "게임 가격 : " + data.price + "원";
  text.textContent = data.text;
  game.style.setProperty("box-shadow", "initial", "important")
  game.style.setProperty("transform", "initial", "important")
  game.style.setProperty("cursor", "initial", "important")
  carBtn.textContent = "장바구니 담기";

  //appendChild 부모자식 위치 설정
  right.appendChild(carBtn);
  left.appendChild(title);
  left.appendChild(genre);
  left.appendChild(price);
  left.appendChild(text);
  lowBox.appendChild(left);
  lowBox.appendChild(right);
  game.appendChild(img);
  game.appendChild(lowBox);
  product.appendChild(game);
}
