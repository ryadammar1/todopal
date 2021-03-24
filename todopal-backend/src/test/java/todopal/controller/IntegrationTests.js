/* Here is the integration testing main script. Run this and the files with the axios calls will run. */

//axios config, dw about this
import axios from "axios";
axios.defaults.baseURL = "http://localhost:8080";

//import assertions
import assert from "assert";

//import your test files here
import { ID012 } from "./ID012.js";
import { ID007 } from "./ID007.js";

//call your test files here
const id012 = await ID012();
const id007 = await ID007();

assert.strictEqual("Kevin", id012[0]);
assert.strictEqual(id007 != null, true);
assert.strictEqual("Kevin", id013.name);
assert.strictEqual("kevin@kevin.ca", id013.email);
assert.strictEqual("1234", id013.password);
assert.strictEqual("-1", id013.classroom.classroomId);
