package hk.ust.comp3021.lab;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.Test;

import base.Note;
import base.NoteBook;
import base.TextNote;

public class JUnitTest {

	@Test
	public void testSearchNote() {
		NoteBook nb = new NoteBook();
		nb.createTextNote("Note1", "Java", "comp3021");
		nb.createTextNote("Note2", "Assignment", "due on 2023-10-13");
		nb.createTextNote("Note3", "lab","need to attend weekly");
		nb.createTextNote("Note4", "lab7","testing");
		List<Note> notes = nb.searchNotes("java or DUE or testing");
		System.out.println(notes.size());
		assertEquals(3, notes.size(), "The size of the search results is not match");
		HashSet<String> titles = new HashSet<>();
		for (Note note : notes) {
			titles.add(note.getTitle());
		}
		HashSet<String> expectedOutputs = new HashSet<>();
		expectedOutputs.add("Java");
		expectedOutputs.add("Assignment");
		expectedOutputs.add("lab7");
		assertEquals(expectedOutputs, titles, "The search results is not match");
	}
	
	// To do
	// Design the second test case which reveals the bug in function unknownFunction()
	@Test
	public void testUnknownFunction() {
		TextNote note = new TextNote("a", "");
		Character expectedOutput = 'a';
		Character actualOutput = note.unknownFunction();
		assertEquals(expectedOutput, actualOutput, "The result does not match");
	}
}


