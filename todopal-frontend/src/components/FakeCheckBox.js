import { useState } from "react";

function Task({ action }) {
  return (
    <div
      style={{ float: "left", marginRight: "1rem" }}
      onClick={() => action()}
    >
      &#9634;
    </div>
  );
}

export default Task;
