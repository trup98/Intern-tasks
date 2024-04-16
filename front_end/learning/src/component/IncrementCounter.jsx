import React, {Component} from 'react';

class IncrementCounter extends Component {

  render() {
    const {count, incrementCount} = this.props
    return (
      <div>
        <button onClick={incrementCount}>Click {count} Here</button>
      </div>
    );
  }
}

export default IncrementCounter;
