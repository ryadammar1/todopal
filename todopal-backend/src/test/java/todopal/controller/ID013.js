import axios from "axios";

//variables
const teacherEmail = "john@john.ca";
const teacherName = "John";
const teacherApprovalCode = "123";
const teacherPassword = "1234";
const teacherBio = "hi";
let classroomId = -1;
const classroomName = "math";
const classroomImage = "he";
const classroomSubject = "math";
const studentName = "Kevin";
const studentEmail = "kevin@kevin.ca";
const studentPassword = "1234";

//main function of id
export const ID013 = async function () {
  await createStudent();
  await setStudentToClassroom();
  return await getStudentsById();
};

//test functions
const createStudent = async () => {
  await axios.post(`/students/${studentName}`, null, {
    params: { email: studentEmail, password: studentPassword },
  });
};

const setStudentToClassroom = async () => {
  await axios.put(`/students/${studentEmail}/${classroomId}`);
};

const getStudentsById = async () => {
  const res = await axios.get(`/students/${studentEmail}`);
  return res.data;
};
