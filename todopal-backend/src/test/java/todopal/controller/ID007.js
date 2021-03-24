import axios from 'axios';

//variables
/**
 * 
 *  | name  | description              | points | start date | due date   | tag  | list  |
    | exist | this is an existing task | 1      | 2021-01-23 | 2021-01-23 | math | Bonus | 
 * 
 * 
 */
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
export const ID007 = async function () {
    await createTask();
    return await deleteTask();
}

//test functions
const createTask = async () => {
    await axios.post(`/create-task/`, null, { params: { id : id, name : name, description : description, tag : tag, category : category , mandatory : isMandatory , points : points, "start-date" : start_date,
        "due-date" : due_date } });
}


const deleteTask = async () => {
    const res = await axios.delete(`/delete-task/${id}`);
    return res.data;
}