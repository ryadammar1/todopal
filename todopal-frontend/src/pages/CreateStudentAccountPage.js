import "../style/createClassroomPage.css";
import { useState } from "react"
import axios from "axios"

function CreateStudentAccountPage() {

const [state, setState] = new useState({
    name: '',
    email:'',
    password:'',
    passConfirm:'',
    classroomId: 0,
});

let person = {
    name: '',
    email:'',
    password:'',
    passConfirm:'',
    classroomId: 0,
}

function submitEvent () {
    console.log(person.name + person.email + person.password + person.passConfirm + + person.classroomId);

    axios.post(`https://jsonplaceholder.typicode.com/users`, person)
    .then(res => {
      console.log(res);
      console.log(res.data);
    })
}

  return (
    <div id="create-class-page-wrapper">
      <section id="input-wrapper-create-class">
        <div id="message-create-class" className="row-create-class">
          Create Student Account
        </div>
        <div id="name-subject-create-class" className="row-create-class">
          <div className="name-subject">
            <div>Full Name</div>
            <input onChange={event => person.name = event.target.value }/>
          </div>
          <div className="name-subject">
            <div>Classroom ID</div>
            <input onChange={event => person.classroomId = event.target.value }/>
          </div>
        </div>

        <div id="name-subject-create-class" className="row-create-class">
          <div className="name-subject">
            <div>Password</div>
            <input onChange={event => person.password = event.target.value }/>
          </div>
          <div className="name-subject">
            <div>Confirm Password</div>
            <input onChange={event => person.passConfirm = event.target.value }/>
          </div>
        </div>
        <div className="row-create-class">
          <div>Email</div>
          <input onChange={event => person.email = event.target.value }/>
        </div>
      <button onClick={()=>submitEvent()}>Create Account</button>
      </section>
    </div>
  );
}

export default CreateStudentAccountPage;