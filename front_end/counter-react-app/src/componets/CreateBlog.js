import {useState} from "react";
import {useHistory} from "react-router-dom/cjs/react-router-dom";

const CreateBlog = () => {

  const [title, setTitle] = useState('');
  const [body, setBody] = useState('');
  const [author, setAuthor] = useState('Mario');
  const [pending, setPending] = useState(false);
  const history = useHistory();
  const handleSubmit = (e) => {
    e.preventDefault();
    const blog = {title, body, author};

    setPending(true);

    fetch('http://localhost:8000/blogs', {
      method: 'POST',
      headers: {"Content-Type": "application/json"},
      body: JSON.stringify(blog)
    }).then(() => {
      console.log("New Blog Added!");
      setPending(false);
      history.push('/');
    })
  }

  return (
    <div className="create">
      <h2>Add New Blog</h2>
      <form onSubmit={handleSubmit}>
        <label>Blog Title:</label>
        <input
          type="text"
          required
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <label>Blog Body:</label>
        <textarea required
                  value={body}
                  onChange={(e) => setBody(e.target.value)}
        ></textarea>
        <label>Blog author:</label>
        <select
          value={author}
          onChange={(e) => setAuthor(e.target.value)}
        >
          <option value="Gs">Gs</option>
          <option value="Ts">Ts</option>
          <option value="Mario">Mario</option>
        </select>
        {!pending && <button>Add Blog</button>}
        {pending && <button disabled>Adding Blog..</button>}
      </form>
    </div>
  );

}

export default CreateBlog;
