import axios from 'axios';

//variables
const teacherEmail = "john24@john.ca";
const teacherName = "John";
const teacherApprovalCode = "123";
const teacherPassword = "1234";
const teacherBio = "hi";


//main function of id
export const ID024 = async function () {
    await createTeacher();
    return await getProfile();
}

//test functions
const createTeacher = async () => {
    await axios.post(`/teachers/${teacherName}`, null, { params: { approvalCode: teacherApprovalCode, email: teacherEmail, bio: teacherBio, password: teacherPassword } });
}

const getProfile = async () => {
    const res = await axios.get(`/teachers/personal/${teacherEmail}`);
    return res.data;
}
