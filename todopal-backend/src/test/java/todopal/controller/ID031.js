import axios from "axios";

//variables
const id = 69;
const name = "TestTask";
// TODO finish the constants here. Make sure the names are good, nothing in this file is good,

//main function of id
export const ID031 = async function () {
  await createStudent();
  await setStudentToClassroom();
  return await getStudentsById();
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
