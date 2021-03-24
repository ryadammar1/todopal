/* Here is the integration testing main script. Run this and the files with the axios calls will run. */

//axios config, dw about this
import axios from 'axios';
axios.defaults.baseURL = 'http://localhost:8080';

//import assertions
import assert from 'assert';

//import your test files here
import { ID012 } from './ID012.js';

//call your test files here
const id012 = await ID012();

assert.strictEqual("Kevin", id012[0]);