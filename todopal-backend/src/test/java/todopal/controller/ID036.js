import axios from 'axios';

//variables
const studentEmail = "student@pain.bread";
const studentName = "Student Bread";
const studentPassword = "pain";

export const ID036 = async () => {
    await createStudent();
    return await getStudentProfile();
}

const createStudent = async () => {
    await axios.post(`/students/${studentName}`, null, { params: { name: studentName, email: studentEmail, password: studentPassword } });
}

const getStudentProfile = async () => {
    const response = await axios.get(`/students/personal/${studentEmail}`);
    return response.data;
}