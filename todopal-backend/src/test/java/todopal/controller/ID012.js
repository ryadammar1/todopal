import axios from 'axios';

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
export const ID012 = async function () {
    await createTeacher();
    await createClassroom();
    await createStudent();
    await setStudentToClassroom();
    return await getStudentsInClassroom();
}

//test functions
const createTeacher = async () => {
    await axios.post(`/teachers/${teacherName}`, null, { params: { approvalCode: teacherApprovalCode, email: teacherEmail, bio: teacherBio, password: teacherPassword } });
}

const createClassroom = async () => {
    await axios.post(`/create-classroom/${classroomName}`, null, { params: { teacherEmail: teacherEmail, imagePath: classroomImage, subject: classroomSubject } }).then((res) => {
        classroomId = res.data.classroomDtoId;
    })
}

const createStudent = async () => {
    await axios.post(`/students/${studentName}`, null, { params: { email: studentEmail, password: studentPassword } });
}

const setStudentToClassroom = async () => {
    await axios.put(`/students/${studentEmail}/${classroomId}`);
}

const getStudentsInClassroom = async () => {
    const res = await axios.get(`/classroom_student_names/${classroomId}`);
    return res.data;
}