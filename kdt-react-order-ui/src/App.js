import "./App.css";
import "bootstrap/dist/css/bootstrap.css";
import React, { useEffect, useState } from "react";
import { ProductList } from "./components/ProductList";
import { Summary } from "./components/Summary";
import axios from "axios";

function App() {
  const [products, setProducts] = useState([
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

  useEffect(() => {
    // side effect로 비동기 통신을 하는 것이 좋다.
    (async () => {
      const { data } = await axios.get("http://localhost:8080/api/v1/products");
      setProducts(data);
    })();
  }, []);

  const onAddsClick = (id) => {
    const product = products.find((product) => product.id === id);
    const found = items.find((item) => item.id === id);
    const updatedItems = found
      ? items.map((item) =>
          item.id === id ? { ...item, count: item.count + 1 } : item
        )
      : [...items, { ...product, count: 1 }];
    setItems(updatedItems);
  };

  const [items, setItems] = useState([]);
  return (
    <div className="container-fluid">
      <div className="row justify-content-center m-4">
        <h1 className="text-center">Grids & Circle</h1>
      </div>
      <div className="card">
        <div className="row">
          <div className="col-md-8 mt-4 d-flex flex-column align-items-start p-3 pt-0">
            <ProductList productDatas={products} onAddsClick={onAddsClick} />
          </div>
          <div className="col-md-4 summary p-4">
            <Summary items={items} />
          </div>
        </div>
      </div>
    </div>
  );
}

export default App;
