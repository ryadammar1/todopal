import { useState } from "react";
import "../style/loginPage.css";

function LoginPage() {
  let currentChoice = "";
  const [isChose, setIsChose] = new useState({ student: {}, teacher: {} });

  function studentOnClick() {
    setIsChose({
      student: { bgColor: "white", color: "#1ace98" },
      teacher: {},
    });
  }

  function teacherOnClick() {
    setIsChose({
      student: {},
      teacher: { bgColor: "white", color: "#1ace98" },
    });
  }

  function loginOnClick() {
    console.log("logged in");
  }

  return (
    <div id="login-page-wrapper">
      <div id="login-content-wrapper">
        <label>LOGIN</label>
        <input type="text" id="email-entry" placeholder="Email" />
        <input type="text" id="password-entry" placeholder="Password" />

        <div id="button-wrapper">
          <button
            id="student-button"
            onClick={() => studentOnClick()}
            style={{
              backgroundColor: isChose.student.bgColor,
              color: isChose.student.color,
            }}
          >
            STUDENT
          </button>
          <button
            id="teacher-button"
            onClick={() => teacherOnClick()}
            style={{
              backgroundColor: isChose.teacher.bgColor,
              color: isChose.teacher.color,
            }}
          >
            TEACHER
          </button>
        </div>

        <div id="login-button-wrapper">
          <button id="login-button" onClick={() => loginOnClick()}>
            LOGIN
          </button>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;
