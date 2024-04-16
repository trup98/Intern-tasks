import React, {Component} from 'react';

class RefDemo extends Component {
  constructor() {
    super();
    this.inputRef = React.createRef();
  }
  // for focusing after reloading
  componentDidMount() {
    this.inputRef.current.focus();
  }

  focus = () => {
    this.inputRef.current.focus()
  }

  render() {
    return (
      <div>
        <input type="text" ref={this.inputRef}/>
        <button onClick={this.focus}>Focus</button>
      </div>
    );
  }
}

export default RefDemo;
