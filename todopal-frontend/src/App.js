import React, { Fragment } from "react";
import TemporaryHomePage from "./pages/TemporaryHomePage";
import CreateClassroomPage from "./pages/CreateClassroomPage";
import CreateTeacherAccountPage from "./pages/CreateTeacherAccountPage";
import Topbar from "./components/Topbar.js";
import CreateStudentAccountPage from "./pages/CreateStudentAccountPage";
import "./style/global/__font.css";

class App extends React.Component {
  setState = this.setState.bind(this);
  constructor(props) {
    super(props);
    this.state = {
      currentPage: "TemporaryHomePage",
    };
  }

  render() {
    if (this.state.currentPage === "TemporaryHomePage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" />
          <TemporaryHomePage setState={this.setState} />
        </Fragment>
      );
    }

    if (this.state.currentPage === "CreateClassroomPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="TeacherClassRoom" />
          <CreateClassroomPage setState={this.setState} />
        </Fragment>
      );
    }

    if (this.state.currentPage === "CreateTeacherAccountPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" />
          <CreateTeacherAccountPage setState={this.setState} />
        </Fragment>
      );
    }

    if (this.state.currentPage === "CreateStudentAccountPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Student" />
          <CreateStudentAccountPage setState={this.setState} />
        </Fragment>
      );
    }
  }
}

export default App;
