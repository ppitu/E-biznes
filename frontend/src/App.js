
import './App.css';
import {
  BrowserRouter as Router,
    Route,
    Link
} from 'react-router-dom';
import Products from "./components/Products";
import Users from "./components/Users";
import SignInModal from "./components/SignInModal";

function App() {
  return <Router>
    <div id="menu">
      <ul>
        <li>
          <Link to="/users">Użytkownicy</Link>
        </li>
        <li>
          <Link to="/login">Logowanie</Link>
        </li>
      </ul>
    </div>
    <Route path="/users" component={Users}/>
    <Route path="/login" component={SignInModal} />
    <Products />
  </Router>
}

export default App;
