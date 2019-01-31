package linked;

public class LinkedListTester {
	private static void fail(String message) {
		if (!message.endsWith("."))
			message += ".";
		System.out.println("... FAIL: " + message);
		System.exit(0);
	}

	public static void main(String[] args) {
		System.out.println("Searching for A in empty list ...");
		CharLinkedList list = new CharLinkedList();
		if (list.find('A') != null)
			fail("Found a node in an empty list.");

		String original = "ABCDE";
		for (int i = 0; i < original.length(); i++) {
			// Find target.
			list = new CharLinkedList(original);
			char target = original.charAt(i);
			System.out.println("Searching for " + target + " in " + original
					+ " ...");
			CharNode node = list.find(target);
			if (node == null)
				fail("Couldn't find it.");
			if (node.getData() != target)
				fail("Found node containing " + node.getData());
			if (!list.hasIntegrity())
				fail("Found " + target + " but list has broken integrity");
			if (!list.toString().equals(original))
				fail("Found " + target + " but list has changed to " + list);

			// Duplicate target.
			list = new CharLinkedList(original);
			System.out.println("Will duplicate " + target + " in " + original
					+ " ...");
			list.duplicate(target);
			if (!list.hasIntegrity())
				fail("After duplicating, list doesn't have integrity");
			String expected = original.substring(0, i + 1)
					+ original.substring(i);
			if (!list.toString().equals(expected))
				fail("After duplicating, list is " + list + ", expected "
						+ expected);
		}

		// Duplicate non-existent char.
		list = new CharLinkedList(original);
		System.out.println("Will duplicate X in " + original + " ...");
		list.duplicate('X');
		if (!list.hasIntegrity())
			fail("After duplicating, list doesn't have integrity");
		if (!list.toString().equals(original))
			fail("List should not have changed, became " + list);

		System.out.println("\nPASS");
	}
}
