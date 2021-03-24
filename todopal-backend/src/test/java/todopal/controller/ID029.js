import axios from "axios";

//variables
let id = 5;
const isMandatory = true;
const name = "exist";
const description = "this is an existing task";
let points = 1;
const start_date = "2021-01-23";
const due_date = "2021-01-25";
const tag = "math";
const category = "homework";

//main function of id
export const ID029 = async function () {
  await createTask();
  return await getTasks();
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

const getTasks = async () => {
  const res = await axios.get(`/all-tasks/`);
  return res.data;
};
