/* Here is the integration testing main script. Run this and the files with the axios calls will run. */

//dotenv config
import dotenv from "dotenv";
dotenv.config();

//axios config, dw about this
import axios from "axios";
axios.defaults.baseURL = process.env.BASE_URL;

//import assertions
import assert from "assert";

//import your test files here
import { ID012 } from "./ID012.js";
import { ID007 } from "./ID007.js";
import { ID013 } from "./ID013.js"; 
import { ID024 } from "./ID024.js";

//import db cleaner
import { clean } from "./DBCleaner.js";

//call your test files here
await clean();
const id012 = await ID012();
await clean();
const id007 = await ID007();
await clean();
const id013 = await ID013();
await clean();
const id024 = await ID024();
await clean();

assert.strictEqual("Kevin", id012[0]);
assert.strictEqual(id007 != null, true);
assert.strictEqual("Kevin", id013.name);
assert.strictEqual("kevin@kevin.ca", id013.email);
assert.strictEqual("1234", id013.password);
