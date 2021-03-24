import axios from "axios";

//variables
const id = 69;
const name = "TestTask";
const description = "none";
const tag = "math";
const category = "homework";
const isMandatory = true;
const points = 1;
const start_date = "2021-01-01";
const due_date = "2021-01-02";
//variables
const studentName = "Kevin";
const studentEmail = "kevin@kevin.ca";
const studentPassword = "1234";

//main function of id
export const ID031 = async function () {
  await createStudent();
  await createTask();
  await setTaskAsDone();
  return await getTask();
};

//test functions
const createTask = async () => {
  await axios.post(`/create-task/`, null, {
    params: {
      id: id,
      name: name,
      description: description,
      tag: tag,
      category: category,
      mandatory: isMandatory,
      points: points,
      "start-date": start_date,
      "due-date": due_date,
    },
  });
};

const setTaskAsDone = async () => {
  await axios.post(`/mark-task-done/`, null, {
    params: {
      id: id,
      email: email,
      feedback: feedback,
    },
  });
};

const getTask = async () => {
  await axios.post(`/task/`, null, {
    params: {
      id: id,
    },
  });
};

const createStudent = async () => {
  await axios.post(`/students/${studentName}`, null, {
    params: { email: studentEmail, password: studentPassword },
  });
};
