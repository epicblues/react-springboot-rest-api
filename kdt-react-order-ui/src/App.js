import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import React, { useState } from "react";

const Product = ({ productName, category, price }) => (
  <>
    <li className="list-group-item d-flex mt-2">
      <div className="col-2">
        <img
          className="img-fluid"
          src="https://i.imgur.com/HKOFQYa.jpeg"
          alt=""
        />
      </div>
      <div className="col">
        <div className="row text-muted">{productName}</div>
        <div className="row">{category}</div>
      </div>
      <div className="col text-center price">{price}원</div>
      <div className="col text-end action">
        <a className="btn btn-small btn-outline-dark" href="">
          추가
        </a>
      </div>
    </li>
  </>
);

const ProductList = ({ productDatas = [] }) => (
  <>
    <h5 className="flex-grow-0">
      <b>상품 목록</b>
    </h5>
    <ul className="list-group products">
      {productDatas.map((data) => (
        <Product {...data} />
      ))}
    </ul>
  </>
);

const Summary = ({ items = [] }) => {
  const totalPrice = items.reduce((pre, curr) => {
    return pre.price * pre.count + curr.price * curr.count;
  }, 0);
  return (
    <>
      <div>
        <h5 className="m-0 p-0">
          <b>Summary</b>
        </h5>
      </div>
      <hr />
      {items.map((item) => (
        <SummaryItem key={item.id} {...item} />
      ))}
      <div className="row">
        <h6 className="p-0">
          Brazil Serra Do Caparaó <span className="badge bg-dark">2개</span>
        </h6>
      </div>
      <div className="row">
        <h6 className="p-0">
          Columbia Nariñó <span className="badge bg-dark">2개</span>
        </h6>
      </div>
      <form>
        <div className="mb-3">
          <label for="email" className="form-label">
            이메일
          </label>
          <input type="email" className="form-control mb-1" id="email" />
        </div>
        <div className="mb-3">
          <label for="address" className="form-label">
            주소
          </label>
          <input type="text" className="form-control mb-1" id="address" />
        </div>
        <div className="mb-3">
          <label for="postcode" className="form-label">
            우편번호
          </label>
          <input type="text" className="form-control" id="postcode" />
        </div>
        <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
      </form>
      <div className="row pt-2 pb-2 border-top">
        <h5 className="col">총금액</h5>
        <h5 className="col text-end">{totalPrice}원</h5>
      </div>
      <button className="btn btn-dark col-12">결제하기</button>
    </>
  );
};

const SummaryItem = ({ id, productName, count }) => (
  <div className="row">
    <h6 className="p-0">
      {productName} <span className="badge bg-dark text-">{count}개</span>
    </h6>
  </div>
);
function App() {
  const [productDatas, setProductDatas] = useState([
    {
      id: "uuid-1",
      productName: "콜롬비아 커피 1",
      category: "커피빈",
      price: 5000,
    },
    {
      id: "uuid-2",
      productName: "콜롬비아 커피 2",
      category: "커피빈",
      price: 10000,
    },
    {
      id: "uuid-3",
      productName: "콜롬비아 커피 3",
      category: "커피빈",
      price: 12000,
    },
  ]);

  const [items, setItems] = useState([{}]);
  return (
    <div className="container-fluid">
      <div className="row justify-content-center m-4">
        <h1 className="text-center">Grids & Circle</h1>
      </div>
      <div className="card">
        <div className="row">
          <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <ProductList productDatas={productDatas} />
          </div>
          <div className="col-md-4 summary p-4">
            <Summary />
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
