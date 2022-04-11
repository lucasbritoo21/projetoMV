import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import logo from './assets/images/logo.svg'
import CreateFood from "./pages/CreateFood";
import CreateUser from "./pages/CreateUser";

import Home from './pages/Home'
import './App.css'
function App() {
  return (
    <Router>
      <div className="app">
        <div className="main__navbar">
          <img src={logo} alt="logo" />
        </div>
        <Switch>
          <Route exact path="/new/user/:id">
            <CreateUser/>
          </Route>
          <Route exact path="/new/user">
            <CreateUser/>
          </Route>

          <Route exact path="/new/food">
            <CreateFood/>
          </Route>

          
          <Route path="/users/:id">
            <div>user</div>
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
