import pg from 'pg';


export const clean = async () => {
    const Pool = pg.Pool;
    const connectionString = process.env.CONNECTION_URI
    const pool = new Pool({connectionString, ssl: {
        rejectUnauthorized: false
      }});
    await pool.query('DELETE FROM student_personal_task');
    await pool.query('DELETE FROM student_school_task');
    await pool.query('DELETE FROM student');
    await pool.query('DELETE FROM task_container');
    await pool.query('DELETE FROM task');
    await pool.query('DELETE FROM teacher_classroom');
    await pool.query('DELETE FROM classroom');
    await pool.query('DELETE FROM teacher');
    pool.end();
}