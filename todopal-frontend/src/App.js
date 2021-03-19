import React, { Fragment } from "react";
import TemporaryHomePage from "./pages/TemporaryHomePage";
import CreateClassroomPage from "./pages/CreateClassroomPage";
import CreateTeacherAccountPage from "./pages/CreateTeacherAccountPage";
import ViewAllTasksInClassroomPage from "./pages/ViewAllTasksInClassroomPage";
import ViewStudentTasksPage from "./pages/ViewStudentTasksPage";
import Topbar from "./components/Topbar.js";
import CreateStudentAccountPage from "./pages/CreateStudentAccountPage";
import CreateTaskListPage from "./pages/CreateTaskListPage";
import CreateCategoryPage from "./pages/CreateCategoryPage";
import ViewStudentProfileAsTeacherPage from "./pages/ViewStudentProfileAsTeacherPage";
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
          <CreateClassroomPage />
        </Fragment>
      );
    }

    if (this.state.currentPage === "CreateTeacherAccountPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" />
          <CreateTeacherAccountPage />
        </Fragment>
      );
    }

    if (this.state.currentPage === "ViewAllTasksInClassroomPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" />
          <ViewAllTasksInClassroomPage
            mandatoryTasks={[
              {
                isMandatory: true,
                taskName: "Math homework",
                tag: "Math",
                description: "I love math",
                category: "Math?",
                startDate: "Jan 1 2021",
                dueDate: "Feb 1 2021",
                pointCount: 1,
              },
              {
                isMandatory: true,
                taskName: "English homework",
                tag: "English",
                description: "I love refregirators",
                category: "eng?",
                startDate: "Jan 1 2021",
                dueDate: "Feb 1 2021",
                pointCount: 1,
              },
            ]}
            optionalTasks={[]}
          />
        </Fragment>
      );
    }
    if (this.state.currentPage === "ViewStudentTasksPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" />
          <ViewStudentTasksPage
            mandatoryList={[
              {
                isMandatory: true,
                taskName: "Math homework",
                tag: "Math",
                description: "I love math",
                category: "Math?",
                startDate: "Jan 1 2021",
                dueDate: "Feb 1 2021",
                pointCount: 1,
              },
              {
                isMandatory: true,
                taskName: "English homework",
                tag: "English",
                description: "I love refregirators",
                category: "eng?",
                startDate: "Jan 1 2021",
                dueDate: "Feb 1 2021",
                pointCount: 1,
              },
            ]}
            optionalList={[
              {
                isMandatory: false,
                taskName: "Math not work",
                tag: "Math",
                description:
                  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
                category: "Math?",
                startDate: "Jan 1 2021",
                dueDate: "Feb 1 2021",
                pointCount: 1,
              },
              {
                isMandatory: false,
                taskName: "English not work",
                tag: "English",
                description: "I love refregirators",
                category: "eng?",
                startDate: "Jan 1 2021",
                dueDate: "Feb 1 2021",
                pointCount: 1,
              },
            ]}
          />
        </Fragment>
      );
    }

    if (this.state.currentPage === "ViewStudentProfileAsTeacherPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" />
          <ViewStudentProfileAsTeacherPage
            mandatoryList={[
              {
                isMandatory: true,
                taskName: "Math homework",
                tag: "Math",
                description: "I love math",
                category: "Math?",
                startDate: "Jan 1 2021",
                dueDate: "Feb 1 2021",
                pointCount: 1,
              },
              {
                isMandatory: true,
                taskName: "English homework",
                tag: "English",
                description: "I love refregirators",
                category: "eng?",
                startDate: "Jan 1 2021",
                dueDate: "Feb 1 2021",
                pointCount: 1,
              },
            ]}
            optionalList={[
              {
                isMandatory: false,
                taskName: "Math not work",
                tag: "Math",
                description:
                  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum",
                category: "Math?",
                startDate: "Jan 1 2021",
                dueDate: "Feb 1 2021",
                pointCount: 1,
              },
              {
                isMandatory: false,
                taskName: "English not work",
                tag: "English",
                description: "I love refregirators",
                category: "eng?",
                startDate: "Jan 1 2021",
                dueDate: "Feb 1 2021",
                pointCount: 1,
              },
            ]}
          />
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

    if (this.state.currentPage === "CreateTaskListPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" />
          <CreateTaskListPage />
        </Fragment>
      );
    }

    if (this.state.currentPage === "CreateCategoryPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Student" />
          <CreateCategoryPage setState={this.setState} />
        </Fragment>
      );
    }
  }
}

export default App;
