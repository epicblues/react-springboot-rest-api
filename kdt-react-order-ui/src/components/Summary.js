import React, { useState } from "react";
import { SummaryItem } from "./SummaryItem";

export const Summary = ({ items, onOrderSubmit }) => {
  const emptyOrder = {
    email: "",
    address: "",
    postcode: "",
  };
  const [order, setOrder] = useState(emptyOrder);

  const handleSubmit = async () => {
    if (order.address === "" || order.email === "" || order.postcode === "") {
      alert("입력 값을 확인해주세요!");
      return;
    }
    console.log(order);
    onOrderSubmit(order);
    setOrder(emptyOrder);
  };

  const totalPrice = items.reduce((pre, curr) => {
    return pre + curr.price * curr.count;
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
        <SummaryItem key={item.productId} {...item} />
      ))}
      <form>
        <div className="mb-3">
          <label htmlFor="email" className="form-label">
            이메일
          </label>
          <input
            type="email"
            value={order.email}
            onChange={({ target: { value } }) =>
              setOrder((order) => ({ ...order, email: value }))
            }
            className="form-control mb-1"
            id="email"
          />
        </div>
        <div className="mb-3">
          <label htmlFor="address" className="form-label">
            주소
          </label>
          <input
            type="text"
            value={order.address}
            className="form-control mb-1"
            id="address"
            onChange={({ target: { value } }) =>
              setOrder((order) => ({ ...order, address: value }))
            }
          />
        </div>
        <div className="mb-3">
          <label htmlFor="postcode" className="form-label">
            우편번호
          </label>
          <input
            type="text"
            value={order.postcode}
            className="form-control"
            id="postcode"
            onChange={({ target: { value } }) =>
              setOrder((order) => ({ ...order, postcode: value }))
            }
          />
        </div>
        <div>당일 오후 2시 이후의 주문은 다음날 배송을 시작합니다.</div>
      </form>
      <div className="row pt-2 pb-2 border-top">
        <h5 className="col">총금액</h5>
        <h5 className="col text-end">{totalPrice}원</h5>
      </div>
      <button className="btn btn-dark col-12" onClick={handleSubmit}>
        결제하기
      </button>
    </>
  );
};
