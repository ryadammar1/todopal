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
import ViewStudentsInClassroomPage from "./pages/ViewStudentsInClassroomPage"
import ViewStudentProfileAsTeacherPage from "./pages/ViewStudentProfileAsTeacherPage";
import ViewTeacherProfilePage from "./pages/ViewTeacherProfilePage";
import ViewPersonalProfileStudentPage from "./pages/ViewPersonalProfileStudentPage"
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
          <Topbar setState={this.setState} name="Teacher" isTeacher={true} />
          <TemporaryHomePage setState={this.setState} />
        </Fragment>
      );
    }

    if (this.state.currentPage === "CreateClassroomPage") {
      return (
        <Fragment>
          <Topbar
            setState={this.setState}
            name="TeacherClassRoom"
            isTeacher={true}
          />
          <CreateClassroomPage />
        </Fragment>
      );
    }

    if (this.state.currentPage === "CreateTeacherAccountPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" isTeacher={true} />
          <CreateTeacherAccountPage />
        </Fragment>
      );
    }

    if (this.state.currentPage === "ViewAllTasksInClassroomPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" isTeacher={true} />
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
          <Topbar setState={this.setState} name="Teacher" isTeacher={true} />
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

    if (this.state.currentPage === "ViewPersonalProfileStudent") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Student" />
          <ViewPersonalProfileStudentPage
            student={{
              studentName: "Johnny",
              studentEmail: "johnny@email.com",
              studentPassword: "johnnyiscool",
              studentBio: "I like watching TV",
              studentPoints: 15,
              studentClassroom: 134,
            }}
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
          <Topbar setState={this.setState} name="Student" isTeacher={false} />
          <CreateStudentAccountPage setState={this.setState} />
        </Fragment>
      );
    }

    if (this.state.currentPage === "ViewStudentsInClassroomPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" />
          <ViewStudentsInClassroomPage studentList={[
              {
                fullName: "Ricky",
                email: "ricky@email.com"
              },
              {
                fullName: "Taha",
                email: "taha@email.com"
              },
              {
                fullName: "Delia",
                email: "delia@email.com"
              },
              {
                fullName: "Zimu",
                email: "zimu@email.com"
              },
              {
                fullName: "Nafiz",
                email: "nafiz@email.com"
              },
              {
                fullName: "Ryad",
                email: "ryad@email.com"
              },
              {
                fullName: "Ahmad",
                email: "ahmad@email.com"
              },
              {
                fullName: "Gurdarshan",
                email: "gurdarshan@email.com"
              },
              {
                fullName: "Daniel",
                email: "daniel@email.com"
              },
              {
                fullName: "Omar",
                email: "omar@email.com"
              },

          ]}/>
        </Fragment>
      );
    }

    if (this.state.currentPage === "CreateTaskListPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" isTeacher={true} />
          <CreateTaskListPage />
        </Fragment>
      );
    }

    if (this.state.currentPage === "CreateCategoryPage") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" isTeacher={true} />
          <CreateCategoryPage setState={this.setState} />
        </Fragment>
      );
    }

    //this is a dummy teacher
    let dummyTeacher = {
      name: "dummy Teacher",
      email: "dummy.teacher@let.com",
      bio: "Im a little dummy teacher",
      password: "iAmPassword",
      apprCode: "123ILoveMath",
      classes: [
        { id: "classid1" },
        { id: "classid2" },
        { id: "classid3" },
        { id: "classid4" },
      ],
    };

    if (this.state.currentPage === "ViewTeacherProfilePageAsTeacher") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" />
          <ViewTeacherProfilePage
            isLoggedInAsTeacher={true}
            teacher={dummyTeacher}
          />
        </Fragment>
      );
    }

    if (this.state.currentPage === "ViewTeacherProfilePageAsStudent") {
      return (
        <Fragment>
          <Topbar setState={this.setState} name="Teacher" />
          <ViewTeacherProfilePage
            isLoggedInAsTeacher={false}
            teacher={dummyTeacher}
          />
        </Fragment>
      );
    }
  }
}

export default App;
