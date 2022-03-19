import React from "react";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import AddCake from "./add-cakes/AddCake";

import "./App.css";
import Login from "./login/Login";
import NavBar from "./navigation/NavBar";
import ViewAllCakes from "./view-cakes/ViewAllCakes";

function App() {
  return (
    <>
      <BrowserRouter>
        <NavBar />
        <Routes>
          <Route path="/" exact={true} element={<ViewAllCakes />} />
          <Route path="/cakes" exact={true} element={<AddCake />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
