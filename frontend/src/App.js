
import './App.css';
import {
  BrowserRouter as Router,
    Route,
    Link
} from 'react-router-dom';
import Products from "./components/Products";
import Users from "./components/Users";

function App() {
  return <Router>
    <div id="menu">
      <ul>
        <li>
          <Link to="/products">Produkty</Link>
        </li>
        <li>
          <Link to="/users">Użytkownicy</Link>
        </li>
      </ul>
    </div>
    <Route path="/products" component={Products}/>
    <Route path="/users" component={Users}/>
  </Router>
}

export default App;
