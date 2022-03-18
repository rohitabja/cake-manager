import React from "react";
import { BrowserRouter, Route, Switch } from "react-router-dom";
import AddCake from "./add-cakes/AddCake";

import "./App.css";
import NavBar from "./navigation/NavBar";
import ViewAllCakes from "./view-cakes/ViewAllCakes";

function App() {
  return (
    <>
      <BrowserRouter>
        <NavBar />
        <Switch>
          <Route path="/" exact={true} component={ViewAllCakes} />
          <Route path="/addCake" exact={true} component={AddCake} />
        </Switch>
      </BrowserRouter>
    </>
  );
}

export default App;
