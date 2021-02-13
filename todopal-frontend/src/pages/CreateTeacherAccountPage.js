import "../style/createClassroomPage.css";
import { useState } from "react"
import axios from "axios"

function CreateTeacherAccountPage() {

const [state, setState] = new useState({
    name: '',
    email:'',
    password:'',
});

let person = {
    name: 'vfvf',
    email:'',
    password:'',
}

function submitEvent () {
    console.log(person.name + person.password + person.email);
    axios.post(`https://jsonplaceholder.typicode.com/users`)
    .then(res => {
      console.log(res);
      console.log(res.data);
    })
}

  return (
    <div id="create-class-page-wrapper">
      <section id="input-wrapper-create-class">
        <div id="message-create-class" className="row-create-class">
          Create Teacher Account
        </div>
        <div id="name-subject-create-class" className="row-create-class">
          <div className="name-subject">
            <div>Full Name</div>
            <input onChange={event => person.name = event.target.value }/>
          </div>
        </div>

        <div id="name-subject-create-class" className="row-create-class">
          <div className="name-subject">
            <div>Email</div>
            <input onChange={event => person.email = event.target.value }/>
          </div>
          <div className="name-subject">
            <div>Password</div>
            <input onChange={event => person.password = event.target.value }/>
          </div>
        </div>
      <button onClick={()=>submitEvent()}>Create Account</button>
      </section>
    </div>
  );
}

export default CreateTeacherAccountPage;