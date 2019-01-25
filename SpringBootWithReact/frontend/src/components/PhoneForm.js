import React, {Component} from 'react'

class PhoneForm extends Component {
    state = {
        name: '',
        phone: ''
    }

    handleChage = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        });
    }

    handleSubmit = (e) => {
        e.preventDefault();
        this.props.onCreate(this.state);
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <input placeholder="이름"
                       value={this.state.name}
                       onChange={this.handleChage}
                       name="name">
                </input>
                <input placeholder="전화번호"
                       value={this.state.phone}
                       onChange={this.handleChage}
                       name="phone">
                </input>
                <div>{this.state.name} {this.state.phone}</div>
                <button type="submit">등록</button>
            </form>
        );
    }
}

export default PhoneForm;