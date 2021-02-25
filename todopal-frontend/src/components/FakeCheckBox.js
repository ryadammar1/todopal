import "../style/fakeCheckBox.css";

function FakeCheckBox({ action }) {
  return (
    <div id="fake-box" onClick={() => action()}>
      &#9634;
    </div>
  );
}

export default FakeCheckBox;
