import "../style/topbar.css";

function Topbar({ setState, name }) {
  function homeOnClickHandler() {
    setState({ currentPage: "TemporaryHomePage" });
  }
  return (
    <div id="topbar-wrapper">
      <button id="home-button" onClick={() => homeOnClickHandler()}>
        Todopal
      </button>

      <div id="small-button-wrapper">
        <button className="small-button">TO-DO</button>
        <button className="small-button">Race</button>
        <button className="small-button">Profile</button>
        <button className="small-button">Settings</button>

        <button id="profile-button">Hello {name}</button>
      </div>
    </div>
  );
}

export default Topbar;
